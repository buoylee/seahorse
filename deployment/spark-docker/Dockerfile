# Copyright (c) 2016, CodiLime Inc.

FROM ubuntu:16.04

ARG SPARK_VERSION
ARG HADOOP_VERSION

# Install packages
# libcurl4-openssl-dev and libssl-dev are needed for IRkernel dependencies
# gfortran liblapack-dev liblapack3 libopenblas-base libopenblas-dev are needed for machine learning R libraries
RUN apt-get update && \
    apt-get install -y build-essential openjdk-8-jre wget curl bzip2 openssh-server openssh-client libsm6 \
    libcurl4-openssl-dev libssl-dev && \
    apt-get install -y gfortran liblapack-dev liblapack3 libopenblas-base libopenblas-dev && \
    apt-get clean && \
    apt-get autoremove && \
    rm -rf /var/lib/apt/lists/*

# Setup passwordless SSH
RUN ssh-keygen -q -N "" -t rsa -f /root/.ssh/id_rsa
RUN cp /root/.ssh/id_rsa.pub /root/.ssh/authorized_keys

# Install Spark
ENV SPARK_PACKAGE spark-$SPARK_VERSION-bin-hadoop$HADOOP_VERSION
ENV SPARK_HOME /opt/spark-$SPARK_VERSION
ENV PATH $PATH:$SPARK_HOME/bin
RUN wget -q -O - \
  "https://archive.apache.org/dist/spark/spark-$SPARK_VERSION/$SPARK_PACKAGE.tgz" \
  | gunzip \
  | tar x -C /tmp/ \
  && mv /tmp/$SPARK_PACKAGE $SPARK_HOME \
  && rm -rf $SPARK_HOME/examples $SPARK_HOME/ec2 \
  && rm -rf $SPARK_HOME/lib/spark-examples*.jar

# Install conda
RUN wget -q -O /tmp/Miniconda2-latest-Linux-x86_64.sh \
    https://repo.anaconda.com/miniconda/Miniconda2-latest-Linux-x86_64.sh && \
    bash /tmp/Miniconda2-latest-Linux-x86_64.sh -f -b -p /opt/conda && \
    rm /tmp/Miniconda2-latest-Linux-x86_64.sh && \
# Install python packages
# Install tornado in version 4.1
# jupyter_client/ioloop/restarter.py (jupyter_client < 5.1) needs tornado in that version
    /opt/conda/bin/conda install --yes \
    'tornado' 'ipykernel' 'jupyter_client' 'pandas' 'matplotlib' \
    'scipy' 'seaborn' 'scikit-learn' && \
#    /opt/conda/bin/conda install -c anaconda libgfortran && \
# Install r packages
    /opt/conda/bin/conda install --yes -c r r-essentials && \
    /opt/conda/bin/conda install --yes -c r r-base && \
    /opt/conda/bin/conda install --yes -c r r-irkernel && \
    /opt/conda/bin/conda clean --all --yes


# Flag to use old g++ ABI (necessary for compilation of r-igraph package)
RUN mkdir -p /root/.R && \
    echo "CXXFLAGS+=-D_GLIBCXX_USE_CXX11_ABI=0" >> /root/.R/Makevars

ENV PATH $PATH:/opt/conda/bin

COPY startup.sh /opt/
RUN chown root.root /opt/startup.sh && chmod 700 /opt/startup.sh

ENTRYPOINT [ "/opt/startup.sh" ]
CMD [ "-d" ]
