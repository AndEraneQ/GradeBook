#include "addbehavior.h"
#include "ui_addbehavior.h"

AddBehavior::AddBehavior(QWidget *parent)
    : QDialog(parent)
    , ui(new Ui::AddBehavior)
{
    ui->setupUi(this);
}

AddBehavior::~AddBehavior()
{
    delete ui;
}
