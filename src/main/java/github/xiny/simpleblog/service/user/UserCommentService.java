package github.xiny.simpleblog.service.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import github.xiny.simpleblog.domain.CommentMessage;
import github.xiny.simpleblog.mapper.CommentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserCommentService {

    @Resource
    private CommentMapper commentMapper;
    public JsonNode getComment(String blogId) throws RuntimeException {

        final List<CommentMessage> commentList = commentMapper.getAllCommentForBlogId(blogId);
        HashMap<Integer,JsonNode> commentMap = new HashMap<>();
        HashMap<Integer, Integer> idMap = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        JsonNode jsonNode;
        try {
            jsonNode  = objectMapper.readTree(objectMapper.writeValueAsString(commentList));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("解析评论失败");
        }
        if (jsonNode.isArray()) {
            for (JsonNode node : jsonNode) {
                final int commentId = node.get("id").asInt();
                    commentMap.put(commentId,node);
                if (node.has("commentCommentId")) {
                    insertComment(node,node, idMap,commentMap);
                }
            }
        }

        try {
            return objectMapper.readTree(objectMapper.writeValueAsString(commentMap.values()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertComment(JsonNode insertCommentNode,JsonNode parentNode,Map<Integer, Integer> idMap, Map<Integer, JsonNode> commentMap) {
        final JsonNode parentCommentNode = parentNode.get("commentCommentId");
        int parentCommentId=-1;
        if (parentCommentNode!=null) {
            parentCommentId = parentCommentNode.asInt();
        }
        if (parentCommentNode!=null&&idMap.containsKey(parentCommentId)) {
            final Integer rootCommentId = idMap.get(parentCommentId);
            final ObjectNode jsonNode = (ObjectNode) commentMap.get(rootCommentId);
            final ArrayNode children = (ArrayNode) jsonNode.get("children");
            final ObjectNode node = (ObjectNode) insertCommentNode;
            node.put("parentUserName",commentMap.get(node.get("commentCommentId").asInt()).get("account").asText());
            if (children==null)
                jsonNode.putArray("children").add(insertCommentNode);
            else{
                children.add(insertCommentNode);
                jsonNode.replace("children",children);
            }
            commentMap.replace(rootCommentId,jsonNode);
            idMap.put(parentCommentId,rootCommentId);
        }else {
            final ObjectNode pNode = (ObjectNode) commentMap.get(parentCommentId);
            if (pNode.has("commentCommentId")) {
                insertComment(insertCommentNode,pNode,idMap,commentMap);
            }else {
                idMap.put(parentCommentId,parentCommentId);
                final ArrayNode children = (ArrayNode) pNode.get("children");
                final ObjectNode node = (ObjectNode) insertCommentNode;
                node.put("parentUserName",pNode.get("account").asText());
                if (children==null)
                    pNode.putArray("children").add(node);
                else{
                    children.add(node);
                    pNode.replace("children",children);
                }
                commentMap.replace(parentCommentId,pNode);
            }
        }
    }


}
