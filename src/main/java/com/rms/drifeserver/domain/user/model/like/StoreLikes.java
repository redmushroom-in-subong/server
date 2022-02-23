package com.rms.drifeserver.domain.user.model.like;

import com.rms.drifeserver.domain.store.model.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue(value = "store")
public class StoreLikes extends Like {
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
}
