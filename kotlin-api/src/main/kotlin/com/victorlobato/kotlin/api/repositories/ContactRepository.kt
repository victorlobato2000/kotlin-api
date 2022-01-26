package com.victorlobato.kotlin.api.repositories

import com.victorlobato.kotlin.api.entities.Contact
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContactRepository : JpaRepository<Contact, Long> {
}