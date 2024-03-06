#ifndef ADDABSENCE_H
#define ADDABSENCE_H

#include <QDialog>

namespace Ui {
class AddAbsence;
}

class AddAbsence : public QDialog
{
    Q_OBJECT

public:
    explicit AddAbsence(QWidget *parent = nullptr);
    ~AddAbsence();

private slots:
    void on_pushButton1_clicked();

private:
    Ui::AddAbsence *ui;
};

#endif // ADDABSENCE_H
