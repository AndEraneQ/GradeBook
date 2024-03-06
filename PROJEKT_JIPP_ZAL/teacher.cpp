#include "teacher.h"
#include <iostream>

Teacher::Teacher(Person p1) {
    this->name=p1.getName();
    this->surename=p1.getSurname();
    this->email=p1.getEmail();
    this->password=p1.getPassword();
    this->role=p1.getRole();
}

void Teacher::printGrades(ofstream &file,int grade){
    try{
        if(file.good() && file.is_open()){
            file << grade;
            file << " ";
        }
        else{
            throw runtime_error("Couldn't open a file");
        }
    } catch(exception &e){
        cerr << e.what() << endl;
    }
}

