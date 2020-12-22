package com.ryums.bookmark.repository.tag;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryums.bookmark.dto.TagDTO;
import com.ryums.bookmark.entity.QMarkEntity;
import com.ryums.bookmark.entity.QTagEntity;
import com.ryums.bookmark.entity.TagEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/*
 * 커스텀 쿼리 메소드 구현 클래스
 * */
public class TagRepositoryImpl extends QuerydslRepositorySupport implements TagRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public TagRepositoryImpl() { super(TagEntity.class); }

    @Override
    public List<TagDTO> getTagList(String tagName, Pageable pageable) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QTagEntity tagEntity = QTagEntity.tagEntity;

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (tagName != null && !tagName.equals("")) {
            booleanBuilder.and(tagEntity.tagName.contains(tagName));
        }

        return jpaQueryFactory.select(Projections.fields(
                TagDTO.class,
                tagEntity.tagIdx,
                tagEntity.tagName,
                tagEntity.imgUrl))
                .from(tagEntity)
                .where(booleanBuilder)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(tagEntity.tagIdx.desc())
                .fetch();
    }

    @Override
    public List<TagDTO> getTagListContainCount(Pageable pageable) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QMarkEntity markEntity = QMarkEntity.markEntity;
        QTagEntity tagEntity = QTagEntity.tagEntity;

        return jpaQueryFactory.select(Projections.fields(
                    TagDTO.class,
                    tagEntity.tagIdx,
                    tagEntity.tagName,
                    tagEntity.imgUrl,
                    markEntity.tagIdx.count().as("tagCount")))
                .from(tagEntity)
                .leftJoin(tagEntity.markEntity, markEntity)
                .groupBy(tagEntity.tagIdx)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(tagEntity.tagIdx.desc())
                .fetch();
    }

    @Override
    public long getTotalTagCount(String tagName) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QTagEntity tagEntity = QTagEntity.tagEntity;

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (tagName != null && !tagName.equals("")) {
            booleanBuilder.and(tagEntity.tagName.contains(tagName));
        }

        return jpaQueryFactory.selectFrom(tagEntity)
                .where(booleanBuilder)
                .fetchCount();
    }
}
