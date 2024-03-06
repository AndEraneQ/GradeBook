/********************************************************************************
** Form generated from reading UI file 'addgrades.ui'
**
** Created by: Qt User Interface Compiler version 6.6.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_ADDGRADES_H
#define UI_ADDGRADES_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QComboBox>
#include <QtWidgets/QDialog>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QSpacerItem>
#include <QtWidgets/QVBoxLayout>

QT_BEGIN_NAMESPACE

class Ui_AddGrades
{
public:
    QVBoxLayout *verticalLayout;
    QHBoxLayout *horizontalLayout;
    QLabel *subjectLabel;
    QLabel *personLabel;
    QLabel *gradeLabel;
    QHBoxLayout *horizontalLayout_2;
    QComboBox *subjectComboBox;
    QComboBox *personComboBox;
    QComboBox *gradeComboBox;
    QHBoxLayout *horizontalLayout_3;
    QSpacerItem *addOrCancelHorizontalSpacer;
    QPushButton *cancelPushButton;
    QPushButton *addPushButton;

    void setupUi(QDialog *AddGrades)
    {
        if (AddGrades->objectName().isEmpty())
            AddGrades->setObjectName("AddGrades");
        AddGrades->resize(503, 186);
        AddGrades->setStyleSheet(QString::fromUtf8("background-color: rgb(90, 90, 90);"));
        AddGrades->setSizeGripEnabled(false);
        verticalLayout = new QVBoxLayout(AddGrades);
        verticalLayout->setObjectName("verticalLayout");
        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setObjectName("horizontalLayout");
        subjectLabel = new QLabel(AddGrades);
        subjectLabel->setObjectName("subjectLabel");
        subjectLabel->setStyleSheet(QString::fromUtf8("font: 700 15pt \"Nirmala UI\";\n"
"color: \"white\";"));

        horizontalLayout->addWidget(subjectLabel);

        personLabel = new QLabel(AddGrades);
        personLabel->setObjectName("personLabel");
        personLabel->setStyleSheet(QString::fromUtf8("font: 700 15pt \"Nirmala UI\";\n"
"color: \"white\";"));

        horizontalLayout->addWidget(personLabel);

        gradeLabel = new QLabel(AddGrades);
        gradeLabel->setObjectName("gradeLabel");
        gradeLabel->setStyleSheet(QString::fromUtf8("font: 700 15pt \"Nirmala UI\";\n"
"color: \"white\";"));

        horizontalLayout->addWidget(gradeLabel);


        verticalLayout->addLayout(horizontalLayout);

        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2->setObjectName("horizontalLayout_2");
        subjectComboBox = new QComboBox(AddGrades);
        subjectComboBox->setObjectName("subjectComboBox");
        subjectComboBox->setStyleSheet(QString::fromUtf8("font: 700 15pt \"Nirmala UI\";\n"
"color: \"white\";"));

        horizontalLayout_2->addWidget(subjectComboBox);

        personComboBox = new QComboBox(AddGrades);
        personComboBox->setObjectName("personComboBox");
        personComboBox->setStyleSheet(QString::fromUtf8("font: 700 15pt \"Nirmala UI\";\n"
"color: \"white\";"));

        horizontalLayout_2->addWidget(personComboBox);

        gradeComboBox = new QComboBox(AddGrades);
        gradeComboBox->setObjectName("gradeComboBox");
        gradeComboBox->setStyleSheet(QString::fromUtf8("font: 700 15pt \"Nirmala UI\";\n"
"color: \"white\";"));

        horizontalLayout_2->addWidget(gradeComboBox);


        verticalLayout->addLayout(horizontalLayout_2);

        horizontalLayout_3 = new QHBoxLayout();
        horizontalLayout_3->setObjectName("horizontalLayout_3");
        addOrCancelHorizontalSpacer = new QSpacerItem(40, 20, QSizePolicy::Expanding, QSizePolicy::Minimum);

        horizontalLayout_3->addItem(addOrCancelHorizontalSpacer);

        cancelPushButton = new QPushButton(AddGrades);
        cancelPushButton->setObjectName("cancelPushButton");
        cancelPushButton->setCursor(QCursor(Qt::OpenHandCursor));
        cancelPushButton->setStyleSheet(QString::fromUtf8("font: 700 15pt \"Nirmala UI\";\n"
"color: \"white\";"));

        horizontalLayout_3->addWidget(cancelPushButton);

        addPushButton = new QPushButton(AddGrades);
        addPushButton->setObjectName("addPushButton");
        addPushButton->setCursor(QCursor(Qt::OpenHandCursor));
        addPushButton->setStyleSheet(QString::fromUtf8("font: 700 15pt \"Nirmala UI\";\n"
"color: \"white\";"));

        horizontalLayout_3->addWidget(addPushButton);


        verticalLayout->addLayout(horizontalLayout_3);


        retranslateUi(AddGrades);

        QMetaObject::connectSlotsByName(AddGrades);
    } // setupUi

    void retranslateUi(QDialog *AddGrades)
    {
        AddGrades->setWindowTitle(QCoreApplication::translate("AddGrades", "Dialog", nullptr));
        subjectLabel->setText(QCoreApplication::translate("AddGrades", "Przedmiot:", nullptr));
        personLabel->setText(QCoreApplication::translate("AddGrades", "Osoba:", nullptr));
        gradeLabel->setText(QCoreApplication::translate("AddGrades", "Ocena:", nullptr));
        cancelPushButton->setText(QCoreApplication::translate("AddGrades", "Anuluj", nullptr));
        addPushButton->setText(QCoreApplication::translate("AddGrades", "Dodaj", nullptr));
    } // retranslateUi

};

namespace Ui {
    class AddGrades: public Ui_AddGrades {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_ADDGRADES_H
