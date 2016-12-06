package org.linker.model.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tags")
public class Tag extends NamedEntity{
    @ManyToMany
    @JoinTable(
            name = "link_tag",
            joinColumns = @JoinColumn(name="tag_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "link_id", referencedColumnName = "id"))
    private List<Link> links;

    private List<Link> getLinksInternal() {
        if(this.links == null) {
            this.links = new ArrayList<>();
        }
        return this.links;
    }

    public Tag() { }

    public Tag(String name) {
        this.setName(name);
    }

    public List<Link> getLinks() {
        return getLinksInternal();
    }

    public void addLink(Link link) {
        this.links.add(link);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
