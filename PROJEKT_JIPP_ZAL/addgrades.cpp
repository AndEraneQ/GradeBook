#include "addgrades.h"
#include "ui_addgrades.h"
#include <vector>
#include <QMessageBox>
#include "teacher.h"
#include <iostream>
#include "fileData.h"
Person person;
AddGrades::AddGrades(QWidget *parent)
    : QDialog(parent)
    , ui(new Ui::AddGrades)
{
    ui->setupUi(this);


    std::ifstream inputFile(directoryName + "usersdata.bin", std::ios::binary);
    if(!inputFile.is_open()){
        QMessageBox::warning(this,"File error","Couldn't open a file");
    }
    try {
        Person *currentUser = new Person;
        while(currentUser->read(inputFile) && !inputFile.eof()){
            if(currentUser->getRole()=="Uczeń"){
                ui->personComboBox->addItem(QString::fromStdString(currentUser->getName() + " " + currentUser->getSurname()));
            }            
        }
        delete currentUser;
    }
    catch (std::exception& e) {
        QMessageBox::warning(this,"Data read error",e.what());
    }
    inputFile.close();

    for(int i=1;i<=6;i++){
        ui->gradeComboBox->addItem(QString::number(i));
    }

    for(auto i=0u;i<person.subjects.size();i++){
        ui->subjectComboBox->addItem(QString::fromStdString(person.subjects[i]));
    }
}

AddGrades::~AddGrades()
{
    delete ui;
}

Teacher teacher(person);
void AddGrades::on_addPushButton_clicked()
{
    QString subject = ui->subjectComboBox->currentText();
    QString nameConnectedWithDot = ui->personComboBox->currentText().replace(" ",".");
    QString grade = ui->gradeComboBox->currentText();
    QString filename= QString::fromStdString(directoryName) + nameConnectedWithDot + subject + ".bin";
    std::ofstream outputFile(filename.toStdString(),std::ios::binary | std::ios::app);
    try{
        teacher.printGrades(outputFile,grade.toInt());
        QMessageBox::information(this,"information","Dodano ocene");
    }
    catch (std::exception &e) {
        QMessageBox::warning(this, "Błąd", e.what());
        return ;

    }
    outputFile.close();
    this->close();
}

void AddGrades::on_cancelPushButton_clicked()
{
    close();
}
