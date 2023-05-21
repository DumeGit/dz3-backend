package dz3.tt.dao;

import dz3.tt.entities.BuyerUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BuyerUserRepositoryImpl implements BuyerUserDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Optional<BuyerUser> findBuyerUserByUsernameAndPassword(String username, String password) {
        TypedQuery<BuyerUser> query = entityManager.createQuery(
                "SELECT u FROM BuyerUser u WHERE u.username = :username AND u.password = :password",
                BuyerUser.class
        );
        query.setParameter("username", username);
        query.setParameter("password", password);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<BuyerUser> findBuyerUserByEmail(String email) {
        TypedQuery<BuyerUser> query = entityManager.createQuery(
                "SELECT u FROM BuyerUser u WHERE u.email = :email",
                BuyerUser.class
        );
        query.setParameter("email", email);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }
}
