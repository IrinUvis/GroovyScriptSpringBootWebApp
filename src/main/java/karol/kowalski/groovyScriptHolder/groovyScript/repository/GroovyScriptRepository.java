package karol.kowalski.groovyScriptHolder.groovyScript.repository;

import karol.kowalski.groovyScriptHolder.groovyScript.domain.GroovyScript;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA repository for handling GroovyScripts in the database.
 */
public interface GroovyScriptRepository extends JpaRepository<GroovyScript, Long> {
}
