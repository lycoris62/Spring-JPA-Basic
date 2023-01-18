package jpabasic.ex1hellojpa;

import jpabasic.ex1hellojpa.hellojpa.Member;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.*;
import java.util.List;

@SpringBootApplication
public class Ex1HelloJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ex1HelloJpaApplication.class, args);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction(); // 모든 변경은 트랜젝션 안에서 처리 해야함.
        tx.begin();
        try {
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(5)
                    .getResultList();
            for (Member member : result) {
                System.out.println("member.getName() = " + member.getName());
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
	}

}
