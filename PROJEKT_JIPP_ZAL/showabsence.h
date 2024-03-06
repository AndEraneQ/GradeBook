#ifndef SHOWABSENCE_H
#define SHOWABSENCE_H

#include <QDialog>

namespace Ui {
class ShowAbsence;
}

class ShowAbsence : public QDialog
{
    Q_OBJECT

public:
    explicit ShowAbsence(QWidget *parent = nullptr);
    ~ShowAbsence();

private:
    Ui::ShowAbsence *ui;
};

#endif // SHOWABSENCE_H
