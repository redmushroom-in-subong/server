package com.rms.drifeserver.domain.like.model;

import com.rms.drifeserver.domain.store.model.Store;
import com.rms.drifeserver.domain.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@DiscriminatorValue(value = "store")
public class StoreLikes extends Like {
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    public StoreLikes(User user, Store store) {
        this.user = user;
        this.store = store;
    }

    public static StoreLikes of(User user, Store store) {
        return new StoreLikes(user, store);
    }
}
