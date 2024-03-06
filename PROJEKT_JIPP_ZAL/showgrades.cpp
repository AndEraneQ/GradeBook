#include "showgrades.h"
#include "ui_showgrades.h"
#include "student.h"
#include <QMessageBox>
#include <iostream>
#include "fileData.h"
ShowGrades::ShowGrades(QWidget *parent)
    : QDialog(parent)
    , ui(new Ui::ShowGrades)
{  
    ui->setupUi(this);
    ui->gradesPlainTextEdit->setReadOnly(true);
    ui->avaragePlainTextEdit->setReadOnly(true);
    ui->finalGradePlainTextEdit->setReadOnly(true);
    Person currentStudent;
    readCurrentUser(this,currentStudent,directoryName + "currentuser.bin");
    if(currentStudent.getRole()=="Nauczyciel"){
        readCurrentUser(this,currentStudent,directoryName + "currentstudent.bin");
    }
    ui->centralLabel->setText(QString::fromStdString("Oceny osoby: " + currentStudent.getName() + " " + currentStudent.getSurname()));
    Student student(currentStudent);
    vector<int> finallyGradesAvarage;
    vector<double> avarageGradesAvarage;
    for(auto i=0u;i<student.subjects.size();i++){
        ui->gradesPlainTextEdit->appendPlainText(QString::fromStdString(student.subjects[i]) + ": ");
        string filename=directoryName + student.getName() + "." + student.getSurname() + student.subjects[i] + ".bin";
        std::ifstream inputFile(filename,std::ios::binary);
        try{
            int singleGrade;
            while(readGrades(inputFile,singleGrade) && !inputFile.eof()){
                student.grades.push_back(singleGrade);
            }
        }
        catch (std::exception& e) {
            std::cerr << "Read data error: " << e.what() << std::endl;
        }
        inputFile.close();
        for(auto k=0u;k<student.grades.size();k++){
            ui->gradesPlainTextEdit->insertPlainText(QString::number(student.grades[k]));
                if(k!=student.grades.size()-1){
                ui->gradesPlainTextEdit->insertPlainText(", ");
            }
        }
        int finallyGrade=0;
        double currentAverange = student.calculateAverange(student.grades);
        while(currentAverange>=1){
            finallyGrade++;
            currentAverange--;
        }
        if(currentAverange>0.50){
            finallyGrade++;
        }
        ui->avaragePlainTextEdit->appendPlainText("Średnia: ");
        ui->finalGradePlainTextEdit->appendPlainText("Ocena Końcowa: ");
        if(student.grades.size()==0){
            ui->avaragePlainTextEdit->insertPlainText("brak");
            ui->finalGradePlainTextEdit->insertPlainText("brak");
        }
        else{
            ui->avaragePlainTextEdit->insertPlainText(QString::number(student.calculateAverange(student.grades)));
            ui->finalGradePlainTextEdit->insertPlainText(QString::number(finallyGrade));
            finallyGradesAvarage.push_back(finallyGrade);
            avarageGradesAvarage.push_back(student.calculateAverange(student.grades));
        }
        student.grades.clear();
    }
    ui->sumOfAvarageOfavarageLabel->setText(QString::number(student.calculateAverange(avarageGradesAvarage)));
    ui->sumOfAvarageOfGradesLabel->setText(QString::number(student.calculateAverange(finallyGradesAvarage)));
}

ShowGrades::~ShowGrades()
{
    delete ui;
}

void ShowGrades::on_cancelPushButton_clicked()
{
    close();
}

