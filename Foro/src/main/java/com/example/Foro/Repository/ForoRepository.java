package com.example.Foro.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Foro.Model.Foro;


@Repository
public interface ForoRepository extends JpaRepository<Foro, Long> {

}
