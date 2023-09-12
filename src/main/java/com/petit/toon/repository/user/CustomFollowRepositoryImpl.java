package com.petit.toon.repository.user;

import com.petit.toon.entity.user.Follow;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomFollowRepositoryImpl implements CustomFollowRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void bulkInsert(List<Follow> follows) {
        jdbcTemplate.batchUpdate(
                "insert into follow(follower_id, followee_id) " +
                        "values (?, ?)",
                getBatchPreparedStatementSetter(follows)
        );

    }

    private BatchPreparedStatementSetter getBatchPreparedStatementSetter(List<Follow> follows) {
        return new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, follows.get(i).getFollower().getId());
                ps.setLong(2, follows.get(i).getFollowee().getId());
            }

            @Override
            public int getBatchSize() {
                return follows.size();
            }
        };
    }
}
