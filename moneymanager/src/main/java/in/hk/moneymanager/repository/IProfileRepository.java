package in.hk.moneymanager.repository;

import in.hk.moneymanager.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IProfileRepository extends JpaRepository<ProfileEntity,Long> {

    //select * from tbl_profiles where email =?;
    Optional<ProfileEntity> findByEmail(String email);

    //select * from tbl_profiles where activation_token = ?;
    Optional<ProfileEntity> findByActivationToken(String activationToken);

}
