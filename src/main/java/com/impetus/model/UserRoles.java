package com.impetus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class UserRoles.
 */
@Entity
@Table(name = "user_roles")
public class UserRoles {
    
    /** The sno. */
    @Id
    @GeneratedValue
    private long sno;
    
    /** The user id. */
    @Column(name = "user_id")
    private String userId;
    
    /** The role. */
    @Enumerated(EnumType.STRING)
    private Roles role;

    /**
     * Gets the sno.
     *
     * @return the sno
     */
    public long getSno() {
        return sno;
    }

    /**
     * Sets the sno.
     *
     * @param sno the new sno
     */
    public void setSno(long sno) {
        this.sno = sno;
    }

    /**
     * Gets the user id.
     *
     * @return the user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the user id.
     *
     * @param userId the new user id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets the role.
     *
     * @return the role
     */
    public Roles getRole() {
        return role;
    }

    /**
     * Sets the role.
     *
     * @param role the new role
     */
    public void setRole(Roles role) {
        this.role = role;
    }

}