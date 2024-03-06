#include <iostream>
#include <QMessageBox>
#include "person.h"
using namespace std;
Person::Person()
{
        this->nameSize = 0;
        this->name = "";

        this->surenameSize = 0;
        this->surename = "";

        this->emailSize = 0;
        this->email = "";

        this->roleSize = 0;
        this->role = "";

        this->passwordSize = 0;
        this->password = "";
}
Person::Person(string name, string surename, string email, string password, string rola)
{
    this->nameSize = name.size();
    this->name = name;

    this->surenameSize = surename.size();
    this->surename = surename;

    this->emailSize = email.size();
    this->email = email;

    this->roleSize = rola.size();
    this->role = rola;

    this->passwordSize = password.size();
    this->password = password;
}

void Person::write(ofstream &file)
{
    try
    {
        if (file.good() && file.is_open())
        {
            nameSize = name.size();
            file.write((char *)&nameSize, sizeof(nameSize));
            file.write((char *)&name[0], nameSize);

            surenameSize = surename.size();
            file.write((char *)&surenameSize, sizeof(surenameSize));
            file.write((char *)&surename[0], surenameSize);

            emailSize = email.size();
            file.write((char *)&emailSize, sizeof(emailSize));
            file.write((char *)&email[0], emailSize);

            roleSize = role.size();
            file.write((char *)&roleSize, sizeof(roleSize));
            file.write((char *)&role[0], roleSize);

            passwordSize = password.size();
            file.write((char *)&passwordSize, sizeof(passwordSize));
            file.write((char *)&password[0], passwordSize);
        }
        else
        {
            throw runtime_error("Couldn't open a filea");
        }
    }
    catch(exception &e)
    {
        std::cerr << e.what() << endl;
    }
}

bool Person::read(ifstream &file)
{
    try
    {
        if (file.good() && file.is_open())
        {
            file.read((char *)&nameSize, sizeof(nameSize));
            name.resize(nameSize);
            file.read((char *)&name[0], nameSize);

            file.read((char *)&surenameSize, sizeof(surenameSize));
            surename.resize(surenameSize);
            file.read((char *)&surename[0], surenameSize);

            file.read((char *)&emailSize, sizeof(emailSize));
            email.resize(emailSize);
            file.read((char *)&email[0], emailSize);

            file.read((char *)&roleSize, sizeof(roleSize));
            role.resize(roleSize);
            file.read((char *)&role[0], roleSize);

            file.read((char *)&passwordSize, sizeof(passwordSize));
            password.resize(passwordSize);
            file.read((char *)&password[0], passwordSize);
            return true;
        }
    }
    catch(exception &e)
    {
        cerr << e.what() << endl;
    }
    return false;
}

string Person::getName()
{
    return name;
}

string Person::getSurname()
{
    return surename;
}

string Person::getEmail()
{
    return email;
}

string Person::getRole()
{
    return role;
}

string Person::getPassword()
{
    return password;
}

void Person::SetName(string name)
{
    this->name = name;
}

void Person::SetSurename(string surname)
{
    this->surename = surname;
}

void Person::SetEmail(string email)
{
    this->email = email;
}

void Person::SetRole(string role)
{
    this->role = role;
}

void Person::SetPassword(string password)
{
    this->password = password;
}

void readCurrentUser(QWidget *parent,Person &currentUser,string namefile){
    std::ifstream inputFile(namefile,std::ios::binary);
    if(!inputFile.is_open()){
        QMessageBox::warning(parent,"Error","Couldn't open a file");
        return;
    }
    try{
        currentUser.read(inputFile);
    }
    catch (std::exception &e){
        std::cerr << "Read data error " << e.what() << std::endl;
    }
    inputFile.close();
}

QMessageBox& operator<<(QMessageBox& msgBox,const Person p1){
    msgBox.setText("Dane osoby\nImie: " + QString::fromStdString(p1.name) + "\nNazwisko: " + QString::fromStdString(p1.surename) + "\nEmail: " + QString::fromStdString(p1.email) + "\nRola: " + QString::fromStdString(p1.role));
    return msgBox;
}


