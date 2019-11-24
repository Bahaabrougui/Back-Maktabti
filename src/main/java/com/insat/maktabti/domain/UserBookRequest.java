package com.insat.maktabti.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UserBookRequest {
    @EmbeddedId
    private Pk pk;
    @ManyToOne
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private Book book;
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "request_id", insertable = false, updatable = false)
    private ExchangeRequest request;

    @Embeddable
    public static class Pk implements Serializable {
        @Column(nullable = false, updatable = false)
        private Long user_id;

        @Column(nullable = false, updatable = false)
        private Long book_id;

        @Column(nullable = false, updatable = false)
        private Long request_id;

        public Pk() {
        }

        public Pk(Long user_id, Long book_id, Long request_id) {
            this.book_id = book_id;
            this.user_id = user_id;
            this.request_id = request_id;
        }

    }
}


