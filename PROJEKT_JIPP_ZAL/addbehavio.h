#ifndef ADDBEHAVIO_H
#define ADDBEHAVIO_H

#include <QDialog>

namespace Ui {
class AddBehavio;
}

class AddBehavio : public QDialog
{
    Q_OBJECT

public:
    explicit AddBehavio(QWidget *parent = nullptr);
    ~AddBehavio();

private slots:
    void on_gradeRadioButton_clicked();

    void on_posOrNegRadioButton_clicked();

    void on_addPushButton_clicked();

    void on_cancelPushButton_clicked();

private:
    Ui::AddBehavio *ui;
};

#endif // ADDBEHAVIO_H
