package ftn.isamrs.tim5.model;
import org.hibernate.annotations.Where;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Where(clause="deleted=0")
public class User extends Person {




	public User() {}


}
