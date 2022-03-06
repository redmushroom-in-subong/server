package com.rms.drifeserver.domain.review.model;

import com.rms.drifeserver.domain.store.model.Store;
import com.rms.drifeserver.domain.user.model.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewKeyword> reviewKeywords = new ArrayList<>();

    private String contents;

    @Transient
    private Long likes;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private Review(User user, Store store, String contents) {
        this.user = user;
        this.store = store;
        this.contents = contents;
    }

    public static Review of(User user, Store store,String contents){
        return new Review(user,store, contents);
    }

    public void addReviewKeyword(List<ReviewKeywordType> reviewKeywordTypes) {
        reviewKeywordTypes.forEach(type -> this.reviewKeywords.add(ReviewKeyword.of(this, type)));
    }
}
