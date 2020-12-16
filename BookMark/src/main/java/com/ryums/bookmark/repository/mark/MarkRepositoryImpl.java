package com.ryums.bookmark.repository.mark;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryums.bookmark.entity.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/*
 * 커스텀 쿼리 메소드 구현 클래스
 * */
public class MarkRepositoryImpl extends QuerydslRepositorySupport implements MarkRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public MarkRepositoryImpl() {
        super(MarkEntity.class);
    }

    @Override
    public List<MarkEntity> findMarkList(String tagName, String markTitle, String useYn, Pageable pageable) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QMarkEntity markEntity = QMarkEntity.markEntity;
        QTagEntity tagEntity = QTagEntity.tagEntity;

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (tagName != null && !tagName.equals("")) {
            booleanBuilder.and(tagEntity.tagName.contains(tagName));
        }
        if (markTitle != null && !markTitle.equals("")) {
            booleanBuilder.and(markEntity.markTitle.contains(markTitle));
        }
        if (useYn != null && !useYn.equals("")) {
            booleanBuilder.and(markEntity.useYn.eq(useYn));
        }

        return jpaQueryFactory.selectFrom(markEntity)
                .leftJoin(markEntity.tagEntity, tagEntity)
                .fetchJoin()
                .where(booleanBuilder)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(markEntity.chgDt.desc())
                .fetch();
    }

    @Override
    public long getMarkCount(String tagName, String markTitle, String useYn) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(this.getEntityManager());
        QMarkEntity markEntity = QMarkEntity.markEntity;
        QTagEntity tagEntity = QTagEntity.tagEntity;

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (tagName != null && !tagName.equals("")) {
            booleanBuilder.and(tagEntity.tagName.contains(tagName));
        }
        if (markTitle != null && !markTitle.equals("")) {
            booleanBuilder.and(markEntity.markTitle.contains(markTitle));
        }
        if (useYn != null && !useYn.equals("")) {
            booleanBuilder.and(markEntity.useYn.eq(useYn));
        }

        return jpaQueryFactory.selectFrom(markEntity)
                .leftJoin(markEntity.tagEntity, tagEntity)
                .where(booleanBuilder)
                .fetchCount();
    }
}
