#include "deletepersonwindow.h"
#include "ui_deletepersonwindow.h"

DeletePersonWindow::DeletePersonWindow(QWidget *parent)
    : QDialog(parent)
    , ui(new Ui::DeletePersonWindow)
{
    ui->setupUi(this);
}

DeletePersonWindow::~DeletePersonWindow()
{
    delete ui;
}
