package entities;

import entities.Person;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-23T09:44:20")
@StaticMetamodel(SwimStyle.class)
public class SwimStyle_ { 

    public static volatile SingularAttribute<SwimStyle, Long> ss_id;
    public static volatile ListAttribute<SwimStyle, Person> persons;
    public static volatile SingularAttribute<SwimStyle, String> styleName;

}