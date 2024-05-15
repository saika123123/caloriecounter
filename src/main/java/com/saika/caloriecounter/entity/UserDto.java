package com.saika.caloriecounter.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data  // これは Lombok によるアノテーションです。これにより、ゲッターやセッター、コンストラクタなどが自動的に生成されます
public class UserDto {  // ユーザーのデータを扱うためのクラス

    @NotEmpty  // ユーザー名は空であってはならないというルール
    private String username;  // ユーザー名を保存するための場所

    @NotEmpty  // パスワードは空であってはならないというルール
    private String password;  // パスワードを保存するための場所

    @NotEmpty  // メールアドレスは空であってはならないというルール
    private String email;  // メールアドレスを保存するための場所
}