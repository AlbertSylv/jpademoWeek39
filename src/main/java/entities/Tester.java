/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dto.PersonSwimDTO;
import java.util.List;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author baske
 */
public class Tester {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em  = emf.createEntityManager();
        
        Person p1 = new Person("Albert", 1997);
        Person p2 = new Person("Ellen", 1997);
        
        Address a1 = new Address("Lang vej 1", 2900, "Hellerup");
        Address a2 = new Address("Kort vej 2", 2800, "Lyngby");
        
        Fee f1 = new Fee(200);
        Fee f2 = new Fee(100);
        Fee f3 = new Fee(300);
        
        
        
        SwimStyle s1 = new SwimStyle("Crawl");
        SwimStyle s2 = new SwimStyle("Smørflyv");
        SwimStyle s3 = new SwimStyle("Bryst");
        
        p1.addSwim(s3);
        p1.addSwim(s2);
        p2.addSwim(s3);
        p2.addSwim(s1);
        
        
        p1.addFee(f1);
        p1.addFee(f3);
        p2.addFee(f2);
        
        
        p1.setAddress(a1);
        p2.setAddress(a2);
        
        
        
        em.getTransaction().begin();
            p1.removeSwim(s3);
            //em.persist(a1);
            //em.persist(a2);
            em.persist(p1);
            em.persist(p2);
            
        
        em.getTransaction().commit();
        
        System.out.println("Person 1: " + p1.getP_id() + ", " + p1.getName());
        System.out.println("Person 2: " + p2.getP_id()+ ", " + p2.getName());
        
        System.out.println("Alberts gade: " + p1.getAddress().getStreet());
        
        System.out.println("Lad os se om to-vejs virker: " + a1.getPerson().getName());
        
        System.out.println("Hvem har betalt f2: " + f2.getPerson().getName());
        
        System.out.println("");
        
        TypedQuery<Fee> q1 = em.createQuery("SELECT f from Fee f", Fee.class);
        List<Fee> fees = q1.getResultList();
        

        for(Fee f: fees){
            System.out.println(f.getPerson().getName() + ": " + f.getAmount()+ " kr., den: " + f.getPayDate() + ", city: " + f.getPerson().getAddress().getCity());
        }
        
        
        // creating join query
        Query joinQ = em.createQuery("SELECT new dto.PersonSwimDTO( p.name, p.year, s.styleName ) FROM Person p JOIN p.styles s");
        
        List<PersonSwimDTO> psDTO = joinQ.getResultList();
        
        for(PersonSwimDTO psDTOs: psDTO){
            System.out.println("Navn: " + psDTOs.getName() + ", year: " + psDTOs.getYear() + ", swimstyle: " + psDTOs.getSwimStyle());
        }
    }
}
