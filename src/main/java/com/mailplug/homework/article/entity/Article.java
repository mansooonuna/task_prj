package com.mailplug.homework.article.entity;

import com.mailplug.homework.util.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Board name;

    @Column(nullable = false)
    @Size(max = 10, message = "글의 제목은 10자 이하로 작성해주세요.")
    private String title;

    @Column(columnDefinition = "text")
    private String contents;

    @Column(nullable = false)
    private String userId;

    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
