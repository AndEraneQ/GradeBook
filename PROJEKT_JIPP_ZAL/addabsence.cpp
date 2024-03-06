#include "addabsence.h"
#include "ui_addabsence.h"

AddAbsence::AddAbsence(QWidget *parent)
    : QDialog(parent)
    , ui(new Ui::AddAbsence)
{
    ui->setupUi(this);
}

AddAbsence::~AddAbsence()
{
    delete ui;
}

void AddAbsence::on_pushButton1_clicked()
{
    close();
}

