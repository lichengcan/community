package com.example.community.result;

import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

/**
 * @author xiankun.geng
 */
@Data
public class PageRequest implements Serializable {

    private static final long SerialVersionUID = 1L;

    /**
     * 默认页面数目大小
     */
    private static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * 当前页面索引
     */
    private int page;

    /**
     * 页面数目大小
     */
    private int perPage;

    public PageRequest() {
    }

    public int getPage() {
        return (page <= 1) ? 1 : page;
    }

    public int getPerPage() {
        return (0 >= perPage) ? DEFAULT_PAGE_SIZE : perPage;
    }

    public Pageable getPageable() {
        return org.springframework.data.domain.PageRequest.of(this.getPage() - 1, this.getPerPage());
    }

}

