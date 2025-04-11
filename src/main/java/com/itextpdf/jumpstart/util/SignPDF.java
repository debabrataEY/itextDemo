package com.itextpdf.jumpstart.util;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.signatures.PdfSigner;
import com.itextpdf.signatures.PdfSignatureAppearance;
import com.itextpdf.signatures.PdfSignatureAppearance.RenderingMode;
import com.itextpdf.signatures.IExternalSignature;
import com.itextpdf.signatures.PrivateKeySignature;
import com.itextpdf.signatures.BouncyCastleDigest;
import com.itextpdf.signatures.DigestAlgorithms;
import com.itextpdf.signatures.IExternalDigest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;

public class SignPDF {
	
	static {
        Security.addProvider(new BouncyCastleProvider());
    }
	
    public static void sign(String src) throws Exception {        
        String dest = "HelloWorld_signed.pdf";
        String keystorePath = "mykeystore.jks";
        String keystorePassword = "go4access";
        String keyAlias = "mykey";

        // Load the keystore
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(new FileInputStream(keystorePath), keystorePassword.toCharArray());

        // Get the private key and certificate
        PrivateKey pk = (PrivateKey) ks.getKey(keyAlias, keystorePassword.toCharArray());
        Certificate[] chain = ks.getCertificateChain(keyAlias);

        // Create the reader and stamper
        PdfReader reader = new PdfReader(src);
        PdfSigner signer = new PdfSigner(reader, new FileOutputStream(dest), true);

        // Create the appearance
        /*PdfSignatureAppearance appearance = signer.getSignatureAppearance();
        appearance.setReason("Document signed");
        appearance.setLocation("Location");
        appearance.setPageRect(new Rectangle(36, 748, 200, 100));
        appearance.setPageNumber(1);
        appearance.setRenderingMode(RenderingMode.GRAPHIC);*/

        // Create the signature
        IExternalSignature pks = new PrivateKeySignature(pk, DigestAlgorithms.SHA256, "BC");
        IExternalDigest digest = new BouncyCastleDigest();
        signer.signDetached(digest, pks, chain, null, null, null, 0, PdfSigner.CryptoStandard.CMS);
    }
}