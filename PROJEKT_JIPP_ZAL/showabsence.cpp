#include "showabsence.h"
#include "ui_showabsence.h"

ShowAbsence::ShowAbsence(QWidget *parent)
    : QDialog(parent)
    , ui(new Ui::ShowAbsence)
{
    ui->setupUi(this);
}

ShowAbsence::~ShowAbsence()
{
    delete ui;
}
