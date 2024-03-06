#include "student.h"
#include <QMessageBox>
#include <iostream>

Student::Student(Person p1) {
    this->name=p1.getName();
    this->surename=p1.getSurname();
    this->email=p1.getEmail();
    this->password=p1.getPassword();
    this->role=p1.getRole();
}

Student::Student(){
    this->name="";
    this->surename="";
    this->email="";
    this->password="";
    this->role="";
}

bool readGrades(ifstream &file,int &grade){
    try{
        if (!file.eof() && file.good() && file.is_open()){
            file>>grade;
            return true;
        }
    }
    catch(exception &e)
    {
        cerr << e.what() << endl;
    }
    return false;
}
