package net.axel.wafasalaf.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "status")
public class Status implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "status name is required.")
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "status")
    Set<RequestStatus> requestStatuses;

    public Status() {
    }

    public Status(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public Status(UUID id, String name, Set<RequestStatus> requestStatuses) {
        this.id = id;
        this.name = name;
        this.requestStatuses = requestStatuses;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<RequestStatus> getRequestStatuses() {
        return requestStatuses;
    }

    public void setRequestStatuses(Set<RequestStatus> requestStatuses) {
        this.requestStatuses = requestStatuses;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", requestStatuses=" + requestStatuses +
                '}';
    }
}
