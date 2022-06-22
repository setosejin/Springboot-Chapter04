package com.rubypaper;

import com.rubypaper.domain.Board;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class JPAClient {
    public static void main(String[] args){
        // Factory Pattern : Factory Builder로 Factory 생성(createEntityManagerFactory 메소드 사용)
        // EntityManagerFactory 객체 생성
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Chapter04");
        // EntityManager 생성
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            Board board = new Board();
            board.setTitle("JPA init");
            board.setWriter("Sejin");
            board.setContent("게시판 프로젝트로 넘어갑니다");
            board.setCreateDate(new Date());
            board.setCnt(0L);

            // 등록 -> 영속화
            entityManager.persist(board);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
