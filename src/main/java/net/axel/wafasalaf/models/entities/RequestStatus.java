package net.axel.wafasalaf.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "requestStatus")
public class RequestStatus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @NotNull
    @Column(name = "changed_date", nullable = false)
    private LocalDateTime changedDate;

    @Column(name = "description")
    private String description;

    public RequestStatus() {
    }

    public RequestStatus(Request request, Status status, LocalDateTime changedDate, String description) {
        this.request = request;
        this.status = status;
        this.changedDate = changedDate;
        this.description = description;
    }

    public RequestStatus(UUID id, Request request, Status status, LocalDateTime changedDate, String description) {
        this(request, status, changedDate, description);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getChangedDate() {
        return changedDate;
    }

    public void setChangedDate(LocalDateTime changedDate) {
        this.changedDate = changedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RequestStatus{" +
                "id=" + id +
                ", request=" + request +
                ", status=" + status +
                ", changeDate=" + changedDate +
                ", description='" + description + '\'' +
                '}';
    }
}
