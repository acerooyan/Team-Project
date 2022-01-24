package com.example.auth.dao.hibernate;

import com.example.auth.dao.AbstractHbDao;
import com.example.auth.dao.IRegistrationTokenDao;
import com.example.auth.entity.RegistrationToken;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateRegistrationTokenDao extends AbstractHbDao<RegistrationToken> implements IRegistrationTokenDao {
    public HibernateRegistrationTokenDao() {
        super.setClazz(RegistrationToken.class);
    }

    @Override
    public RegistrationToken getTokenByTokenAndEmail(String email, String token) {
        Session session = super.getCurrentSession();
        DetachedCriteria criteria = DetachedCriteria.forClass(RegistrationToken.class);
        criteria.add(Restrictions.eq("token", token));
        criteria.add(Restrictions.eq("email", email));
        return (RegistrationToken) criteria.getExecutableCriteria(session).uniqueResult();
    }

    @Override
    public RegistrationToken addRegistrationToken(RegistrationToken registrationToken) {
        return super.merge(registrationToken);
    }
}
