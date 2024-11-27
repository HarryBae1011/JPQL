package jpql;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            /*
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            //타입 쿼리
            TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);
            //getResultList
            List<Member> resultList = query1.getResultList();
            TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class);
            //getSingleResult
            String singleResult = query2.getSingleResult();


            //일반 쿼리
            Query query3 = em.createQuery("select m.username, m.age from Member m");


            //파라미터 바인딩
            TypedQuery<Member> query4 = em.createQuery("select m from Member m where m.username = :username", Member.class);
            query4.setParameter("username", "member1");
            Member singleResult1 = query4.getSingleResult();
            System.out.println("singleResult1 = " + singleResult1.getUsername());


            //여러 값 new로 조회
            List<MemberDTO> resultList1 = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
                    .getResultList();

            MemberDTO memberDTO = resultList1.get(0);
            System.out.println("memberDTO = " + memberDTO.getUsername());
            System.out.println("memberDTO = " + memberDTO.getAge());
             */

            /*
            for (int i = 0; i < 100; i++) {
                Member member = new Member();
                member.setUsername("member" + i);
                member.setAge(i);

                Team team = new Team();
                team.setName("teamA");
                em.persist(team);
                em.persist(member);
            }

            em.flush();
            em.clear();

            //페이징 API
            List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();

            System.out.println("result.size = " + result.size());
            for (Member member1 : result) {
                System.out.println("member1 = " + member1);
            }
             */

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member");
            member.setAge(10);
            member.setTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

            String query = "select m from Member m left join Team t on m.username = t.name";
            List<Member> result = em.createQuery(query, Member.class)
                    .getResultList();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
