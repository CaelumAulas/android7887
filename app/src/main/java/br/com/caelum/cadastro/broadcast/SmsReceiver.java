package br.com.caelum.cadastro.broadcast;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.widget.Toast;

import br.com.caelum.cadastro.application.CadastroApplication;
import br.com.caelum.cadastro.bancodedadoos.AlunoDAO;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Object[] pdus = (Object[]) intent.getSerializableExtra("pdus");

        byte[] pdu = (byte[]) pdus[0];

        SmsMessage sms = SmsMessage.createFromPdu(pdu);

        String telefone = sms.getDisplayOriginatingAddress();


        CadastroApplication application =
                (CadastroApplication) context.getApplicationContext();

        AlunoDAO alunoDAO = application.getAlunoDAO();

        Integer qtdDeAlunos = alunoDAO.existeTelefone(telefone);



        if (qtdDeAlunos > 0) {
            Toast.makeText(context, "Finge que tá tocando a musica", Toast.LENGTH_LONG).show();
        }

    }
}
