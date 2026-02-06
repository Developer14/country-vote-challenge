package dev.loopstudio.countryvoteapi.repository;

import dev.loopstudio.countryvoteapi.repository.model.CountryVoteModel;
import dev.loopstudio.countryvoteapi.repository.projection.CountryVotesProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Country vote repository.
 * @author Victor Morales
 */
@Repository
public interface CountryVoteRepository extends JpaRepository<CountryVoteModel, Integer> {

    @Query(value = """
            select
                country, count(country) as votes
            from country_vote group by country
            order by votes desc limit :limit;
            """, nativeQuery = true)
    List<CountryVotesProjection> getMostVotedCountries(@Param("limit") int limit);
}
