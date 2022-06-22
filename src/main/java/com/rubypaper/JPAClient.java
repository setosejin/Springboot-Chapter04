package com.rubypaper;

import com.rubypaper.domain.Board;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class JPAClient {
    public static void main(String[] args){
        // Factory Pattern : Factory Builder로 Factory 생성(createEntityManagerFactory 메소드 사용)
        // EntityManagerFactory 객체 생성
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Chapter04");
        // EntityManager 생성
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Transaction 생성(획득)
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            // Transaction begin
            entityTransaction.begin();

            Board board = new Board();
            board.setTitle("JPA init");
            board.setWriter("Sejin");
            board.setContent("게시판 프로젝트로 넘어갑니다");
            board.setCreateDate(new Date());
            board.setCnt(0L);

            // 등록 -> 영속화
            // [DML] INSERT, UPDATE, DELETE는 안 됨 <- Commit을 해줘야 함.(transaction 관리)
            entityManager.persist(board);

            // Transaction commit
            entityTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();

            // 문제가 발생할 시 Transaction rollback
            entityTransaction.rollback();
        } finally {
            // 역순으로 close
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
