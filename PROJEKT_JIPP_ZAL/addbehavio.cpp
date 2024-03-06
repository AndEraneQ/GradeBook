#include "addbehavio.h"
#include "ui_addbehavio.h"
#include "comments.h"
#include "person.h"
#include <iostream>
#include <QMessageBox>
#include "fileData.h"
#include "qdir"
AddBehavio::AddBehavio(QWidget *parent)
    : QDialog(parent)
    , ui(new Ui::AddBehavio)
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
        QMessageBox::warning(this,"error",e.what());
    }
    inputFile.close();
}

AddBehavio::~AddBehavio()
{
    delete ui;
}

void AddBehavio::on_posOrNegRadioButton_clicked()
{
    ui->gradeOrPosOrNegCommComboBox->clear();
    ui->gradeOrPosOrNegCommComboBox->addItems({"Pozytywna", "Negatywna"});
}

void AddBehavio::on_gradeRadioButton_clicked()
{
    ui->gradeOrPosOrNegCommComboBox->clear();
    for(int i=1;i<=6;i++){
        ui->gradeOrPosOrNegCommComboBox->addItem(QString::number(i));
    }
}

void AddBehavio::on_cancelPushButton_clicked()
{
    this->close();
}

void AddBehavio::on_addPushButton_clicked()
{
    if((!ui->posOrNegRadioButton->isChecked()&&!ui->gradeRadioButton->isChecked()) || ui->descriptionLineEdit->text()==""){
        QMessageBox::warning(this,"Bad data error","You need to complete full data");
        return;
    }
    QString commentDescription = ui->descriptionLineEdit->text();
    QString namesConnectedWithDot = ui->personComboBox->currentText().replace(" ", ".");
    if(ui->posOrNegRadioButton->isChecked()){
        QString type = ui->gradeOrPosOrNegCommComboBox->currentText();
        PositiveOrNegativeComment posNegComment(commentDescription.toStdString(),type.toStdString());
        string filename= directoryName + namesConnectedWithDot.toStdString() + "behaviorpORn.bin";
        std::ofstream outputFile(filename, std::ios::binary | std::ios::app);
        try {
            posNegComment.writeToFile(outputFile);
            outputFile.close();
        }
        catch (std::exception &e) {
            QMessageBox::warning(this, "Error", e.what());
            return;
        }
    }
    else if(ui->gradeRadioButton->isChecked()){
        int grade = ui->gradeOrPosOrNegCommComboBox->currentText().toInt();
        GradeComment gradeComm(grade,commentDescription.toStdString());
        string filename = directoryName + namesConnectedWithDot.toStdString() + "behaviorGrade.bin";
        std::ofstream outputFile(filename, std::ios::binary | std::ios::app);
        try {
            gradeComm.writeToFile(outputFile);
            outputFile.close();
        }
        catch (std::exception &e) {
            QMessageBox::warning(this, "Error", e.what());
            return;
        }
    }
    QMessageBox::information(this,"Success","Added behavior succesfully");
    this->close();
}


