#include "registerwindow.h"
#include "ui_registerwindow.h"
#include <QMessageBox>
#include <qpixmap>
#include "person.h"
#include <iostream>
#include <QFile>
#include "fileData.h"
bool RegisterWindow::registercheck(string imie, string nazwisko, string email, string haslo, string rola){
    if(imie==""||nazwisko==""||email==""||haslo==""||rola=="brak"){
        QMessageBox::warning(this,"Register error","Complete data correctly.");
        return false;
    }
    if(imie.length()<3){
        QMessageBox::warning(this,"Registration error","The name must have at least 3 letters.");
        return false;
    }
    if(nazwisko.length()<3){
        QMessageBox::warning(this,"Registration error","The last name must have at least 3 letters.");
        return false;
    }
    if(email.length()<5){
        QMessageBox::warning(this,"Registration error","The email must have at least 5 letters.");
        return false;
    }
    if(haslo.length()<8){
        QMessageBox::warning(this,"Registration error","The password must have at least 8 letters.");
        return false;
    }
    return true;
}

void RegisterWindow::makeBinnaryFile(Person p1,string namefile){
    string File =directoryName + p1.getName() + "." + p1.getSurname() + namefile;
    QFile file(QString::fromStdString(File));
    if(file.open(QIODevice::WriteOnly)){
        file.close();
    }
}

RegisterWindow::RegisterWindow(QWidget *parent)
    : QDialog(parent)
    , ui(new Ui::RegisterWindow)
{
    ui->setupUi(this);
    QPixmap pix(":/1/a/registerWindowPicture.png");
    ui->photoLabel->setPixmap(pix.scaled(200,200,Qt::KeepAspectRatio));
}

RegisterWindow::~RegisterWindow()
{
    delete ui;
}

void RegisterWindow::on_cancelPushButton_clicked()
{
    this->close();
}

void RegisterWindow::on_registerPushButton_clicked()
{
    Person currentUser;
    bool check=false;
    bool registeredCorrectly = false;
    currentUser.SetName(ui->nameLineEdit->text().toStdString());
    currentUser.SetSurename(ui->lastNameLineEdit->text().toStdString());
    currentUser.SetEmail(ui->emailLineEdit->text().toStdString());
    currentUser.SetRole("brak");
    currentUser.SetPassword(ui->passwordLineEdit->text().toStdString());
    if(ui->teacherRadioButton->isChecked()){
        currentUser.SetRole("Nauczyciel");
    }
    if(ui->studentRadioButton->isChecked()){
        currentUser.SetRole("Uczeń");
    }
    std::ifstream inputFile(directoryName + "usersdata.bin", std::ios::binary);
    try {
        Person checkUser;
        while(inputFile && !inputFile.eof()){
            checkUser.read(inputFile);
            if(currentUser.getName()==checkUser.getName() && currentUser.getSurname()==checkUser.getSurname()){
                check=true;
                QMessageBox::warning(this,"Error","User exist");
                break;
            }
        }
    } catch (std::exception& e) {
        std::cerr << "Read data error " << e.what() << std::endl;
    }
    inputFile.close();
    if(registercheck(currentUser.getName(),currentUser.getSurname(),currentUser.getEmail(),currentUser.getPassword(),currentUser.getRole()) && !check){
        std::ofstream outputFile(directoryName + "usersdata.bin", std::ios::binary | std::ios::app);
        try {
            currentUser.write(outputFile);
            outputFile.close();
        } catch (std::exception &e) {
            QMessageBox::warning(this, "Błąd", e.what());
            return ;
        }
        QMessageBox::information(this, "Register", "Registered successfully");
        registeredCorrectly=true;
        if(currentUser.getRole()=="Uczeń"){
            for(auto i=0u;i<currentUser.subjects.size();i++){
                makeBinnaryFile(currentUser,currentUser.subjects[i]+".bin");
            }
            makeBinnaryFile(currentUser,"behaviorGrade.bin");
            makeBinnaryFile(currentUser,"behaviorpORn.bin");
        }
    }
    if(registeredCorrectly){
        ui->nameLineEdit->clear();
        ui->lastNameLineEdit->clear();
        ui->emailLineEdit->clear();
        ui->passwordLineEdit->clear();
        ui->teacherRadioButton->setChecked(false);
        ui->studentRadioButton->setChecked(false);
    }
}



