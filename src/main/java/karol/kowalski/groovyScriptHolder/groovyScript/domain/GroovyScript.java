package karol.kowalski.groovyScriptHolder.groovyScript.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

@Entity(name = "groovy_script")
public class GroovyScript {

    @Id
//    @SequenceGenerator(name = "groovy_script_generator", sequenceName = "groovy_script_sequence", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groovy_script_generator")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String scriptName;
    private String scriptText;

    public GroovyScript() {
    }

    public GroovyScript(String scriptName, String scriptText) {
        this.scriptName = scriptName;
        this.scriptText = scriptText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public String getScriptText() {
        return scriptText;
    }

    public void setScriptText(String scriptText) {
        this.scriptText = scriptText;
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("id", id)
                .append("scriptName", scriptName)
                .append("scriptText", scriptText)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        GroovyScript that = (GroovyScript) o;

        return new EqualsBuilder().append(id, that.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }
}
