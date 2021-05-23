package karol.kowalski.groovyScriptHolder.groovyScript.repository;

import karol.kowalski.groovyScriptHolder.groovyScript.domain.GroovyScript;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroovyScriptRepository extends JpaRepository<GroovyScript, Long> {
}
