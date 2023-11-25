package com.sportify.application.data.entity.venue;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.sportify.application.data.entity.AbstractEntity;
import com.sportify.application.data.entity.enums.BookingStatus;
import com.sportify.application.data.utilities.Pair;
import com.vladmihalcea.hibernate.type.array.EnumArrayType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class SeatingSection extends AbstractEntity {

    

}
