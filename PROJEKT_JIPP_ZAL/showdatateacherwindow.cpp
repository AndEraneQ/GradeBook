#include "showdatateacherwindow.h"
#include "ui_showdatateacherwindow.h"
#include "person.h"
#include <iostream>
#include "fileData.h"
ShowDataTeacherWindow::ShowDataTeacherWindow(QWidget *parent)
    : QDialog(parent)
    , ui(new Ui::ShowDataTeacherWindow)
{
    ui->setupUi(this);
    std::ifstream inputFile(directoryName + "usersdata.bin", std::ios::binary);
    if(!inputFile.is_open()){
        QMessageBox::warning(this,"Error","Couldn't open a file");
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
        std::cerr << "Read data error: " << e.what() << std::endl;
    }
    inputFile.close();
}

ShowDataTeacherWindow::~ShowDataTeacherWindow()
{
    delete ui;
    delete showGrades;
    delete showBehavior;
}

void ShowDataTeacherWindow::on_cancelPushButton_clicked()
{
    close();
}

void ShowDataTeacherWindow::on_nextPushButton_clicked()
{
    Person showDataPerson;
    QStringList personData = ui->personComboBox->currentText().split(" ");
    std::ifstream inputFile(directoryName + "usersdata.bin", std::ios::binary);
    if(!inputFile.is_open()){
        QMessageBox::warning(this,"Error","Couldn't open a file");
    }
    try {
        while(inputFile && !inputFile.eof()){
            showDataPerson.read(inputFile);
            if(showDataPerson.getRole()=="Uczeń" && showDataPerson.getName()==personData.first().toStdString() && showDataPerson.getSurname()==personData.last().toStdString()){
                break;
            }
        }
    }
    catch (std::exception& e) {
        std::cerr << "Read data error: " << e.what() << std::endl;
    }
    inputFile.close();
    std::ofstream outputFile(directoryName + "currentstudent.bin", std::ios::binary | std::ios::trunc);
    try {
        showDataPerson.write(outputFile);
    }
    catch (std::exception &e) {
        QMessageBox::warning(this, "Błąd", e.what());
        return ;
    }
    outputFile.close();
    if(ui->gradesRadioButton->isChecked()){
        close();
        showGrades=new ShowGrades(this);
        showGrades->show();
    }
    else if(ui->behaviorRadioButton->isChecked()){
        close();
        showBehavior=new ShowBehavior(this);
        showBehavior->show();
    }
    else{
        QMessageBox::warning(this,"Error","Select data to display.");
    }
}

