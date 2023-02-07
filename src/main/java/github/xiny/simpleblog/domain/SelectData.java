package github.xiny.simpleblog.domain;

import lombok.Data;

import java.util.List;
@Data
public class SelectData<T> {
     int page;

    int total;

    List<T> data;

    public SelectData(int page, int size, List<T> data) {
        this.page = page;
        this.total = size;
        this.data = data;
    }

    public SelectData(int page, int size) {
        this.page = page;
        this.total = size;
    }
}
