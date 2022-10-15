/*
 * Created on 11 Oct 2022 ( Time 22:31:58 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.apptmyz.fpgreetings.entity;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "user_registration_data"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="user_registration_data", catalog="greetings" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="UserRegistrationData.countAll", query="SELECT COUNT(x) FROM UserRegistrationData x" )
} )
public class UserRegistrationData implements Serializable
{
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Integer    id           ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="email", length=150)
    private String     email        ;

    @Column(name="name", length=150)
    private String     name         ;

    @Column(name="password", length=150)
    private String     password     ;

    @Temporal(TemporalType.DATE)
    @Column(name="dob")
    private Date       dob          ;

    @Column(name="photo", length=45)
    private String     photo        ;

    @Column(name="tag_name", length=145)
    private String     tagName      ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="requested_timestamp")
    private Date       requestedTimestamp ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_timestamp")
    private Date       createdTimestamp ;

    @Column(name="active_flag")
    private Integer    activeFlag   ;

    @Column(name="created_by", length=45)
    private String     createdBy    ;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public UserRegistrationData()
    {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setId( Integer id )
    {
        this.id = id ;
    }
    public Integer getId()
    {
        return this.id;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : email ( VARCHAR ) 
    public void setEmail( String email )
    {
        this.email = email;
    }
    public String getEmail()
    {
        return this.email;
    }

    //--- DATABASE MAPPING : name ( VARCHAR ) 
    public void setName( String name )
    {
        this.name = name;
    }
    public String getName()
    {
        return this.name;
    }

    //--- DATABASE MAPPING : password ( VARCHAR ) 
    public void setPassword( String password )
    {
        this.password = password;
    }
    public String getPassword()
    {
        return this.password;
    }

    //--- DATABASE MAPPING : dob ( DATE ) 
    public void setDob( Date dob )
    {
        this.dob = dob;
    }
    public Date getDob()
    {
        return this.dob;
    }

    //--- DATABASE MAPPING : photo ( VARCHAR ) 
    public void setPhoto( String photo )
    {
        this.photo = photo;
    }
    public String getPhoto()
    {
        return this.photo;
    }

    //--- DATABASE MAPPING : tag_name ( VARCHAR ) 
    public void setTagName( String tagName )
    {
        this.tagName = tagName;
    }
    public String getTagName()
    {
        return this.tagName;
    }

    //--- DATABASE MAPPING : requested_timestamp ( DATETIME ) 
    public void setRequestedTimestamp( Date requestedTimestamp )
    {
        this.requestedTimestamp = requestedTimestamp;
    }
    public Date getRequestedTimestamp()
    {
        return this.requestedTimestamp;
    }

    //--- DATABASE MAPPING : created_timestamp ( DATETIME ) 
    public void setCreatedTimestamp( Date createdTimestamp )
    {
        this.createdTimestamp = createdTimestamp;
    }
    public Date getCreatedTimestamp()
    {
        return this.createdTimestamp;
    }

    //--- DATABASE MAPPING : active_flag ( INT ) 
    public void setActiveFlag( Integer activeFlag )
    {
        this.activeFlag = activeFlag;
    }
    public Integer getActiveFlag()
    {
        return this.activeFlag;
    }

    //--- DATABASE MAPPING : created_by ( VARCHAR ) 
    public void setCreatedBy( String createdBy )
    {
        this.createdBy = createdBy;
    }
    public String getCreatedBy()
    {
        return this.createdBy;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(email);
        sb.append("|");
        sb.append(name);
        sb.append("|");
        sb.append(password);
        sb.append("|");
        sb.append(dob);
        sb.append("|");
        sb.append(photo);
        sb.append("|");
        sb.append(tagName);
        sb.append("|");
        sb.append(requestedTimestamp);
        sb.append("|");
        sb.append(createdTimestamp);
        sb.append("|");
        sb.append(activeFlag);
        sb.append("|");
        sb.append(createdBy);
        return sb.toString(); 
    } 

}