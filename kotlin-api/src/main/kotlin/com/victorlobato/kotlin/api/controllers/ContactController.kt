package com.victorlobato.kotlin.api.controllers

import com.victorlobato.kotlin.api.entities.Contact
import com.victorlobato.kotlin.api.repositories.ContactRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.persistence.EntityNotFoundException
import javax.validation.Valid

@RestController
@RequestMapping("/contacts")
class ContactController {
    @Autowired
    lateinit var repository: ContactRepository // that means I'm going to assign it later on

    @GetMapping
    fun retrieveAllContacts(): List<Contact> = repository.findAll()

    @PostMapping
    fun createNewContact(@Valid @RequestBody contact: Contact) : Contact {
        return repository.save(contact)
    }

    @GetMapping("/{id}")
    fun showContact(@PathVariable("id") id: Long) : Contact {
        return repository.findById(id).orElseThrow { EntityNotFoundException() }
    }

    @PutMapping("/{id}")
    fun updateContact(@PathVariable("id") id: Long, @Valid @RequestBody newContact: Contact) : Contact {
        val contact = repository.findById(id).orElseThrow { EntityNotFoundException() }

        contact.apply {
            this.name = newContact.name
            this.email = newContact.email
        }

        return repository.save(contact)
    }

    @DeleteMapping("/{id}")
    fun deleteContact(@PathVariable("id") id: Long){
        val contact = repository.findById(id).orElseThrow { EntityNotFoundException() }
        repository.delete(contact)
    }


}