/********************************************************************************
** Form generated from reading UI file 'addbehavio.ui'
**
** Created by: Qt User Interface Compiler version 6.6.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_ADDBEHAVIO_H
#define UI_ADDBEHAVIO_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QComboBox>
#include <QtWidgets/QDialog>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QRadioButton>
#include <QtWidgets/QVBoxLayout>

QT_BEGIN_NAMESPACE

class Ui_AddBehavio
{
public:
    QVBoxLayout *verticalLayout_4;
    QVBoxLayout *verticalLayout;
    QLabel *personLabel;
    QComboBox *personComboBox;
    QVBoxLayout *verticalLayout_2;
    QRadioButton *posOrNegRadioButton;
    QRadioButton *gradeRadioButton;
    QVBoxLayout *verticalLayout_3;
    QComboBox *gradeOrPosOrNegCommComboBox;
    QLabel *label;
    QLineEdit *descriptionLineEdit;
    QHBoxLayout *horizontalLayout_2;
    QPushButton *cancelPushButton;
    QPushButton *addPushButton;

    void setupUi(QDialog *AddBehavio)
    {
        if (AddBehavio->objectName().isEmpty())
            AddBehavio->setObjectName("AddBehavio");
        AddBehavio->resize(343, 309);
        AddBehavio->setStyleSheet(QString::fromUtf8("background-color: rgb(90, 90, 90);"));
        verticalLayout_4 = new QVBoxLayout(AddBehavio);
        verticalLayout_4->setObjectName("verticalLayout_4");
        verticalLayout = new QVBoxLayout();
        verticalLayout->setObjectName("verticalLayout");
        personLabel = new QLabel(AddBehavio);
        personLabel->setObjectName("personLabel");
        personLabel->setStyleSheet(QString::fromUtf8("font: italic 14pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        verticalLayout->addWidget(personLabel);

        personComboBox = new QComboBox(AddBehavio);
        personComboBox->setObjectName("personComboBox");
        personComboBox->setCursor(QCursor(Qt::ArrowCursor));
        personComboBox->setStyleSheet(QString::fromUtf8("font: italic 14pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        verticalLayout->addWidget(personComboBox);


        verticalLayout_4->addLayout(verticalLayout);

        verticalLayout_2 = new QVBoxLayout();
        verticalLayout_2->setObjectName("verticalLayout_2");
        posOrNegRadioButton = new QRadioButton(AddBehavio);
        posOrNegRadioButton->setObjectName("posOrNegRadioButton");
        posOrNegRadioButton->setStyleSheet(QString::fromUtf8("font: italic 14pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        verticalLayout_2->addWidget(posOrNegRadioButton);

        gradeRadioButton = new QRadioButton(AddBehavio);
        gradeRadioButton->setObjectName("gradeRadioButton");
        gradeRadioButton->setStyleSheet(QString::fromUtf8("font: italic 14pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        verticalLayout_2->addWidget(gradeRadioButton);


        verticalLayout_4->addLayout(verticalLayout_2);

        verticalLayout_3 = new QVBoxLayout();
        verticalLayout_3->setObjectName("verticalLayout_3");
        gradeOrPosOrNegCommComboBox = new QComboBox(AddBehavio);
        gradeOrPosOrNegCommComboBox->setObjectName("gradeOrPosOrNegCommComboBox");
        gradeOrPosOrNegCommComboBox->setCursor(QCursor(Qt::ArrowCursor));
        gradeOrPosOrNegCommComboBox->setStyleSheet(QString::fromUtf8("font: italic 14pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        verticalLayout_3->addWidget(gradeOrPosOrNegCommComboBox);

        label = new QLabel(AddBehavio);
        label->setObjectName("label");
        label->setLayoutDirection(Qt::LeftToRight);
        label->setStyleSheet(QString::fromUtf8("font: italic 14pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        verticalLayout_3->addWidget(label);

        descriptionLineEdit = new QLineEdit(AddBehavio);
        descriptionLineEdit->setObjectName("descriptionLineEdit");
        descriptionLineEdit->setStyleSheet(QString::fromUtf8("font: italic 14pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        verticalLayout_3->addWidget(descriptionLineEdit);


        verticalLayout_4->addLayout(verticalLayout_3);

        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2->setObjectName("horizontalLayout_2");
        cancelPushButton = new QPushButton(AddBehavio);
        cancelPushButton->setObjectName("cancelPushButton");
        cancelPushButton->setCursor(QCursor(Qt::OpenHandCursor));
        cancelPushButton->setStyleSheet(QString::fromUtf8("font: italic 14pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        horizontalLayout_2->addWidget(cancelPushButton);

        addPushButton = new QPushButton(AddBehavio);
        addPushButton->setObjectName("addPushButton");
        addPushButton->setCursor(QCursor(Qt::OpenHandCursor));
        addPushButton->setStyleSheet(QString::fromUtf8("font: italic 14pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        horizontalLayout_2->addWidget(addPushButton);


        verticalLayout_4->addLayout(horizontalLayout_2);


        retranslateUi(AddBehavio);

        QMetaObject::connectSlotsByName(AddBehavio);
    } // setupUi

    void retranslateUi(QDialog *AddBehavio)
    {
        AddBehavio->setWindowTitle(QCoreApplication::translate("AddBehavio", "Dialog", nullptr));
        personLabel->setText(QCoreApplication::translate("AddBehavio", "Osoba:", nullptr));
        posOrNegRadioButton->setText(QCoreApplication::translate("AddBehavio", "Pozytywna lub negatywna", nullptr));
        gradeRadioButton->setText(QCoreApplication::translate("AddBehavio", "Ocena", nullptr));
        label->setText(QCoreApplication::translate("AddBehavio", "Komentarz: ", nullptr));
        cancelPushButton->setText(QCoreApplication::translate("AddBehavio", "Anuluj", nullptr));
        addPushButton->setText(QCoreApplication::translate("AddBehavio", "Dodaj", nullptr));
    } // retranslateUi

};

namespace Ui {
    class AddBehavio: public Ui_AddBehavio {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_ADDBEHAVIO_H
