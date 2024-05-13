package com.saika.caloriecounter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saika.caloriecounter.entity.User;

// UserRepositoryというインターフェースを作成します。JpaRepositoryを拡張して、UserオブジェクトとそれらのIDとしてLong型を扱えるようにします。
public interface UserRepository extends JpaRepository<User, Long> {
    // ユーザー名でユーザーを探すメソッド。ユーザー名をパラメータとして渡すと、そのユーザー名を持つユーザーをデータベースから探して返します。
    User findByUsername(String username);
}